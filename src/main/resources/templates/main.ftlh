<#import "parts/common.ftlh"as C>
<@C.page>
    <label>Конвертер валют</label>

    <div>
        <form method="post">
            Сконвертировать из
            <div>
                <label>
                    <select name="convertFrom">
                        <#list currencies as cur>
                            <option value="${cur.id}">${cur.charCode} (${cur.name})</option>
                        </#list>
                    </select>
                    <input name="value" type="text" value="${value!""}"/>
                </label>
            </div>

            В
            <div>
                <label>
                    <select name="convertTo">
                        <#list currencies as cur>
                            <option value="${cur.id}">${cur.charCode} (${cur.name})</option>
                        </#list>
                    </select>
                    <input name="result" type="text" value="${result!""}"/>
                </label>
            </div>

            <div><input type="submit" value="Конвертировать"/></div>
            <div>${message!" "}</div>
        </form>
    </div>

    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выйти"/>
        </form>
    </div>

    <H2>История запросов</H2>
    <table border="1">
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
                <td>${history.date?date}</td>
            </tr>
        <#else >
            История запросов отсутсвует
        </#list>
    </table>
</@C.page>