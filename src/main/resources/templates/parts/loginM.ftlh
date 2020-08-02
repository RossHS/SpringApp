<#macro login path,buttonName>
    <form action="${path}" method="post">
        <div><input type="text" name="username" placeholder="Введите логин"/></div>
        <div><input type="password" name="password" placeholder="Введите пароль"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="${buttonName}"/></div>
    </form>
</#macro>