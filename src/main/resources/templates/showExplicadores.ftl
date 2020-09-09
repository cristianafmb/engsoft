<!DOCTYPE html>
<html>
<head>
    <title>Show explicadores</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<h2>List of explicadores</h2>

<tr>
    <th>Id</th>
    <th>Name</th>
    <th>Email</th>
</tr>

<list  as explicador>
<tr>
    <td>${explicador.id}</td>
    <td>${explicador.nome}</td>
    <td>${explicador.email}</td>
</tr>
</list>
</body>
</html>