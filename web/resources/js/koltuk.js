const container = document.querySelector('.container');
let secildi = 0;
const koltukstring = document.getElementById('koltukstring');
const satinal = document.getElementsByClassName('satinal')[0];

let ticketPrice = 250;//+movieSelect.value;

container.addEventListener('click', e => {
    if (
            e.target.classList.contains('seat') &&
            !e.target.classList.contains('occupied') &&
            secildi === 0
            ) {
        e.target.classList.toggle('selected');


        secildi = e.target.classList[1];

        koltukstring.innerText = secildi + " numaralı koltuğu seçtiniz.";

        satinal.style.visibility = 'visible';

        document.getElementById("koltukForm:koltukno").value = secildi;
    } else if (
            e.target.classList.contains('seat') &&
            !e.target.classList.contains('occupied') &&
            e.target.classList.contains('selected')
            ) {
        e.target.classList.toggle('selected');

        secildi = 0;

        koltukstring.innerText = ""
        satinal.style.visibility = 'hidden';

        document.getElementById("koltukForm:koltukno").value = "";
    }
});