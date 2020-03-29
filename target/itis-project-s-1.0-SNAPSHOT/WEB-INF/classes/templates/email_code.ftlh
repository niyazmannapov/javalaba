<#include "base.ftlh">
<#macro content>
    <div>
        <h1> ${user.login}!</h1>
        <br>
        <br>

        <h5>
            ${user.confirmCode}
        </h5>
        <br>

    </div>
</#macro>

<@main/>