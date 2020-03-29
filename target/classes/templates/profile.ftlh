<#include "base.ftlh">

<#macro title>Profile</#macro>

<#macro content>
    <div class="container-fluid" style="padding-top: 10px">
            <br>
            <div class="row">
                <div class="rounded col-md-12 background-login" align="center">
                     <div class="text-red text-center">
                         <form action="/files" method="get">
                             <div class="text-center">
                                 <button type="submit" class="btn btn-danger text-center">Загрузить!</button>
                             </div>
                         </form>
                         <form action="/files/file" method="post">
                             <div class="form-group">
                                 <input type="text" class="form-control" id="inputUsername" placeholder="Enter the path of file"
                                        name="filename">
                             </div>

                         </form>
                     </div>
                </div>
            </div>

    </div>
</#macro>

<@main/>