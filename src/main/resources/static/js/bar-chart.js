document.addEventListener("DOMContentLoaded", () => {
    let bars;
    bars = document.querySelectorAll(".bar");

    bars.forEach(bar => {
        // Definir altura com base no valor em data-value
        let value;
        value = bar.getAttribute("data-value");
        bar.style.height = `${value}%`;

        // Adicionar interação ao clicar (opcional)
        bar.addEventListener("click", () => {
            alert(`Você clicou na barra com valor de ${value}%!`);
        });
    });
});