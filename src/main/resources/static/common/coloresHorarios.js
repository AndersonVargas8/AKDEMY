var colores = ["#ff6961", "#77dd77", "#fdfd96", "#84b6f4", "#fdcae1", "#fcb7af", "#b0f2c2", "#d8af97", "#ff9688", "#ffda9e","#c5c6c8","#b2e2f2","#a0d995","#c8ca66","#dcd9f8","#d8f79a","#95fab9","#ba9df4"];
    if(typeof(materias) === 'undefined')
        var materias = new Map();


    $(document).ready(function () {
        let cols = document.getElementsByName("labelMateria");
        let i = 0;
        while (i < cols.length) {
            let div = cols.item(i).parentElement;
            let materia = cols.item(i++).textContent;
            div.style.backgroundColor = _color(materia);
        }
        $("#tabla-horarios").removeClass("loading");    
    })

    function _hash(key) {
        let hash = 0;
        for (let i = 0; i < key.length; i++) {
            hash += key.charCodeAt(i);
        }

        return hash;
    }

    function _color(materia) {
        let colorId = materias.get(materia);

        if (colorId != null) {
            return colores[colorId];
        }

        let coloresUsados = Array.from(materias.values());
        let hash = _hash(materia);
        colorId = hash % colores.length;

        let iterator = 0;
        while (iterator < colores.length) {

            if (coloresUsados.includes(colorId)) {
                colorId = ++colorId % colores.length;
                iterator++;
            } else {
                break;
            }
        }
        //console.log(materias)
        materias.set(materia, colorId);
        return colores[colorId];
    }