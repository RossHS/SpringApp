<#import "parts/common.ftlh"as C>
<@C.page>
    <label>История запросов</label>
    <table>
        <tr>
            <th>Исходная валюта</th>
            <th>Целевая валюта</th>
            <th>Исходная сумма</th>
            <th>Получаемая сумма</th>
            <th>Дата</th>
        </tr>

        <#list histories as history>
            <tr>
                <td>${history.fromCurrency.name}</td>
                <td>${history.toCurrency.name}</td>
                <td>${history.valueFrom}</td>
                <td>${history.valueTo}</td>
                <td>${history.date}</td>
            </tr>
        <#else >
            История запросов отсутсвует
        </#list>
    </table>
</@C.page>