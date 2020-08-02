<#import "parts/common.ftlh"as C>
<@C.page>
    <label>Конвертер Валют!</label>
    <form action="/main">
        <button>Ввойти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>

    <form action="/registration">
        <button>Зарегистрироваться</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@C.page>