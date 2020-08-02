<#import "parts/common.ftlh"as C>
<@C.page>
    <label>Конвертер валют</label>

    <div>
        <form action="/currencyConverter" method="get">
            <div>
                <label>
                    <select name="convertFrom">
                        <#list currencies as cur>
                            <option value="${cur.id}">${cur.charCode} (${cur.name})</option>
                        </#list>
                    </select>
                    <input name="value" type="number"/>
                </label>
            </div>

            <div>
                <label>
                    <select name="convertTo">
                        <#list currencies as cur>
                            <option value="${cur.id}">${cur.charCode} (${cur.name})</option>
                        </#list>
                    </select>
                </label>
            </div>

            <div><input type="submit" value="Конвертировать"/></div>
        </form>
    </div>

    <div>
        <form action="/usage_history">
            <button>История</button>
        </form>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выйти"/>
        </form>
    </div>
</@C.page>