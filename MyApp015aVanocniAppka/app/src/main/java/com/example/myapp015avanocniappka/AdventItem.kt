package com.example.myapp015avanocniappka


data class AdventItem(
    val title: String,
    val text: String,
    val imageName: String
)

val sampleItems = listOf(
    AdventItem(
        title = "Sváteční ranní procházka",
        text = "Začněte den krátkou procházkou v zasněžené krajině. Vychutnejte si klid a čerstvý vzduch, načerpejte inspiraci pro sváteční náladu a nezapomeňte si vzít s sebou šálek horké kávy nebo čaje.",
        imageName = "prochazka.jpg"
    ),
    AdventItem(
        title = "Zázvorové cukroví",
        text = "Ingredience:\n• 200 g hladké mouky\n• 100 g medu\n• 50 g mletého zázvoru\n• 1 vejce\n• 1 lžička prášku do pečiva\n\nPostup:\n1. Smíchejte suché ingredience.\n2. Přidejte med a vejce a vypracujte těsto.\n3. Vyválejte těsto na cca 5 mm a vykrajujte tvary.\n4. Pečte při 180 °C 10–12 minut.\n5. Nechte vychladnout a posypte moučkovým cukrem.",
        imageName = "zazvorove_cukrovi.jpg"
    ),
    AdventItem(
        title = "Domácí svařené víno",
        text = "Nalijte 750 ml červeného vína do hrnce. Přidejte plátky pomeranče, 2–3 tyčinky skořice a 3–4 hřebíčky. Zahřívejte na mírném ohni, aby se nápoj jen jemně provařil, a podávejte v horkých hrncích.",
        imageName = "svarene_vino.jpg"
    )
)

