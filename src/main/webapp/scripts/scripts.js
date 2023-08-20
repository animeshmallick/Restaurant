function validateLoginForm(){
    var tableId = document.loginForm.tableID.value;

    if (tableId == null || tableId === ""){
        document.getElementById("tableIDSpan").innerHTML="Invalid Table ID.";
        return false;
    } else { document.getElementById("tableIDSpan").innerHTML=""; }

    if (tableId.length !== 6) {
        document.getElementById("tableIDSpan").innerHTML="Invalid Table ID.";
        return false;
    } else { document.getElementById("tableIDSpan").innerHTML="." }

    if (isNaN(tableId)) {
        document.getElementById("tableIDSpan").innerHTML="Invalid Table ID.";
        return false;
    } else { document.getElementById("tableIDSpan").innerHTML="" }
    return true;
}


function validateBookingForm(){
    const name=document.bookingForm.name.value;
    const phone=document.bookingForm.phone.value;
    const guest=document.bookingForm.guest.value;

    if (name==null || name === ""){
        document.getElementById("nameSpan").innerHTML="Not a valid name.";
        return false;
    } else { document.getElementById("nameSpan").innerHTML="Valid."; }

    if (phone.length !== 10) {
        document.getElementById("phoneSpan").innerHTML="Invalid phone number.";
        return false;
    } else { document.getElementById("phoneSpan").innerHTML="Valid."; }

    if (isNaN(phone)) {
        document.getElementById("phoneSpan").innerHTML="Enter Numeric value only.";
        return false;
    } else { document.getElementById("phoneSpan").innerHTML="Valid."; }

    if (isNaN(guest)) {
        document.getElementById("guestSpan").innerHTML="Enter Numeric value only.";
        return false;
    } else { document.getElementById("guestSpan").innerHTML="Valid."; }

    if (guest < 1) {
        document.getElementById("guestSpan").innerHTML="Number of guest should be greater than 0";
        return false;
    } else { document.getElementById("guestSpan").innerHTML="Valid."; }

    return true;
}


function addToCart(i) {
    let row = i.substring(10);
    let itemID = document.getElementById("ProductID" + row).innerHTML;
    let quantity = document.getElementById("ProductQuantityDropdown" + row).value;
    let cookieName = "cart";
    let cookieValue = itemID.concat("Q").concat(quantity);
    let oldCookieValue = getCartCookieValue();
    let newCookieValue = oldCookieValue.concat("AND").concat(cookieValue);
    document.cookie = cookieName.concat("=").concat(newCookieValue);
}

function getCartCookieValue() {
    let cookie = document.cookie;
    let firstIndex = cookie.indexOf('cart=') + 5;
    if(firstIndex < 5 || firstIndex > cookie.length)
        return "";
    cookie = cookie.substring(firstIndex);
    let lastIndex = cookie.indexOf(';');
    if(lastIndex === -1)
        return cookie;
    return cookie.substring(0,lastIndex);
}
