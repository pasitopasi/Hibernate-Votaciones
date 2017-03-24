<%-- 
    Document   : cambioContra
    Created on : 18-feb-2017, 11:50:36
    Author     : pasito
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../CSS/estilo.css" title="style" />
        <style type="text/css">
        </style>
        <script type="text/javascript" src="../JavaScript/ja.js">
        </script>
        <title>Menu</title>
    </head>
    <body>

        <form method="post" action="../ServletDispatcher" onsubmit="return validar();">
            <table id="tabla1">
                <tr>
                    <td colspan="2" align="center"><h3><strong>Cambio de contraseña</strong></h3></td>
                </tr>
                <tr>
                    <th>Contraseña</th>
                    <td><label><input name="contrasena" type="password" id="contrasena" /></label></td>
                </tr>
                <tr>
                    <th>Repita contraseña</th>
                    <td><label><input name="contrasena1" type="password" id="contrasena1" /></label></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <label><input type="submit" name="boton" id="Cambiar" value="Cambiar"/></label>
                    </td>
                </tr>
            </table>
        </form>
                <form method="post" action="../ServletDispatcher">
            <div id="header">
                <ul class="nav">
                    <li><a href="">Usuario: 
                            <%
                                String s = (String) session.getAttribute("dni");
                                out.print(s);
                            %>
                        </a>
                        <ul>
                            <li><input class="bot" type="submit" name="boton" id="desc" value="Desconectar"/></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </form>
        <div id="padre"></div>
    </body>
</html>
