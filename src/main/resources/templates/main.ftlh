<#import "parts/common.ftlh"as C>
<@C.page>
    <label>Конвертер валют</label>

    <div>
        <form action="/main" method="get">
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
        <form action="/usage_history">
            <button>История</button>
        </form>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выйти"/>
        </form>
    </div>
</@C.page>