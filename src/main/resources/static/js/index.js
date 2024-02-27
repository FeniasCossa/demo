function atualizouSelect(seletedValue){
        $('.id').hide();
        $('.prov_' + seletedValue).show();
        console.log("Esse e o valor selecionado"+seletedValue);
}
var selectedValueGeral;
function atualizouSelectCurso(seletedValue) {
                $('.id').hide();
                $('.disc_' + seletedValue).show();
                console.log("Esse e o valor selecionado"+seletedValue);
    selectedValueGeral = seletedValue;
}

