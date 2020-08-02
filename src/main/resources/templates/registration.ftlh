<#import "parts/common.ftlh"as C>
<#import "parts/loginM.ftlh" as L>
<@C.page>

    ${message!" " }

    <div>
        <label>Регистрация</label>
        <@L.login path= "/registration" buttonName="Зарегистрироваться"/>
    </div>
</@C.page>