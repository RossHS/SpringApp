package com.khapilov.currency.converter.service.implementation;

import com.khapilov.currency.converter.domain.Currency;
import com.khapilov.currency.converter.domain.Rate;
import com.khapilov.currency.converter.repos.CurrencyRepo;
import com.khapilov.currency.converter.repos.RateRepo;
import com.khapilov.currency.converter.service.CurrencyImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ross Khapilov
 * @version 1.0 02.08.2020
 */
@Service
public class CurrencyImpServiceImplementation implements CurrencyImpService {
    @Value(value = "${cbr.url:http://www.cbr.ru/scripts/XML_daily.asp?date_req=}")
    private String currencyDataUrl;

    private RateRepo rateRepo;
    private CurrencyRepo currencyRepo;

    private Map<String, List<Rate>> ratesFromDb = new HashMap<>();
    private List<Currency> currencyFromDB;


    public CurrencyImpServiceImplementation() {
    }

    @Autowired
    public CurrencyImpServiceImplementation(CurrencyRepo currencyRepo, RateRepo rateRepo) {
        this.currencyRepo = currencyRepo;
        this.rateRepo = rateRepo;
    }

    @Override
    public void importCurrency() {
        GregorianCalendar currentDate = new GregorianCalendar();
        importCurrency(currentDate);
    }

    @Override
    public void importCurrency(GregorianCalendar date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setCalendar(date);
        String dateString = format.format(date.getTime());
        String currencyUrl = currencyDataUrl + dateString;

        NodeList currencyNodes = getCurrencyNodes(currencyUrl);
        for (int i = 0; i < currencyNodes.getLength(); i++) {
            Element currencyNode = (Element) currencyNodes.item(i);
            String currencyId = currencyNode.getAttribute("ID");

            Currency currency = getCurrencyById(currencyId);
            if (currency == null) {
                currency = saveCurrency(currencyNode);
            }

            Rate rate = getRateByCbrIdAndDate(currencyId, date);
            if (rate == null) {
                saveRate(currency, date, currencyNode);
            }
        }

        currencyRepo.flush();
        rateRepo.flush();
    }

    private NodeList getCurrencyNodes(String url) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url);

            return document
                    .getDocumentElement()
                    .getElementsByTagName("Valute");
        } catch (Exception e) {
            e.printStackTrace();

            return new NodeList() {
                @Override
                public Node item(int index) {
                    return null;
                }

                @Override
                public int getLength() {
                    return 0;
                }
            };
        }
    }

    private Currency getCurrencyById(String id) {
        return getCurrencyFromDb()
                .stream()
                .filter(v -> v.getCbrId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private Rate getRateByCbrIdAndDate(String cbrId, GregorianCalendar date) {
        return getRatesFromDb(date)
                .stream()
                .filter(r -> r.getCurrency().getCbrId().equals(cbrId))
                .findFirst()
                .orElse(null);
    }

    private List<Currency> getCurrencyFromDb() {
        if (currencyFromDB == null) {
            currencyFromDB = currencyRepo.findAll(
                    Sort.by("name")
            );
        }
        return currencyFromDB;
    }

    private List<Rate> getRatesFromDb(GregorianCalendar date) {
        String key = getRateKey(date);

        if (!ratesFromDb.keySet().contains(key)) {
            List<Rate> rates = rateRepo.findAllByDateOrderByValueAsc(date.getTime());
            ratesFromDb.put(key, rates);
        }

        return ratesFromDb.get(key);
    }

    private String getRateKey(GregorianCalendar date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setCalendar(date);

        return format.format(date.getTime());
    }

    private void saveRate(Currency valute, GregorianCalendar date, Element valuteNode) {
        Rate rate = new Rate();
        rate.setCurrency(valute);
        rate.setDate(date.getTime());
        rate.setValue(Double.valueOf(valuteNode
                        .getElementsByTagName("Value")
                        .item(0)
                        .getTextContent()
                        .replace(',', '.')
                )
        );

        rateRepo.save(rate);
    }

    private Currency saveCurrency(Element currencyNode) {
        Currency currencySaved = currencyRepo.findByCbrId(currencyNode.getAttribute("ID"));
        if (currencySaved != null) {
            return currencySaved;
        }

        Currency currency = new Currency();
        currency.setCbrId(currencyNode.getAttribute("ID"));

        currency.setName(currencyNode
                .getElementsByTagName("Name")
                .item(0)
                .getTextContent()
        );
        currency.setNumCode(Integer.valueOf(
                currencyNode
                        .getElementsByTagName("NumCode")
                        .item(0)
                        .getTextContent()
                )
        );
        currency.setCharCode(currencyNode
                .getElementsByTagName("CharCode")
                .item(0)
                .getTextContent()
        );
        currency.setNominal(Integer.valueOf(
                currencyNode
                        .getElementsByTagName("Nominal")
                        .item(0)
                        .getTextContent()
                )
        );
        currencyRepo.save(currency);
        return currency;
    }
}
