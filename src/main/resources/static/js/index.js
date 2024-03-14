function atualizouSelect(seletedValue) {
    $('.id').hide();
    $('.prov_' + seletedValue).show();
    console.log("Esse e o valor selecionado" + seletedValue);
}

function atualizouSelectCurso(seletedValue) {
    $('.id').hide();
    $('.disc_' + seletedValue).show();
    console.log("Esse e o valor selecionado" + seletedValue);
}

