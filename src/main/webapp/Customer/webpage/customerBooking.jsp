<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 21/08/2023
  Time: 02:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="font-size: 16px;" lang="en">
<head>
    <title>CustomerBooking</title>
</head>
<body data-lang="en">
<section class="u-clearfix u-section-1" id="sec-f4e7">
    <div class="u-clearfix u-sheet u-sheet-1">
        <h1 class="u-text u-text-default u-text-1">Enter Customer Details</h1>
        <div class="u-form u-form-1">
        <form name="bookingForm"
              class="u-clearfix u-form-spacing-10 u-form-vertical u-inner-form"
              style="padding: 10px;"
              method="GET"
              action="<%=request.getContextPath()%>/CustomerBooking">
            <div class="u-form-group u-form-name">
                <label for="name" class="u-label">Name</label>
                <input type="text" placeholder="Enter your Name" id="name" name="name" class="u-input u-input-rectangle" required="">
            </div>
            <div class="u-form-group u-form-phone u-form-group-2">
                <label for="phone" class="u-label">Phone</label>
                <input type="tel" pattern="\+?\d{0,3}[\s\(\-]?([0-9]{2,3})[\s\)\-]?([\s\-]?)([0-9]{3})[\s\-]?([0-9]{2})[\s\-]?([0-9]{2})"
                       placeholder="Enter your phone (e.g. +14155552675)"
                       id="phone" name="phone"
                       class="u-input u-input-rectangle" required="">
            </div>
            <div class="u-form-group u-form-number u-form-number-layout-range-number u-form-group-3">
                <label for="range" class="u-label">Guests</label>
                <div class="u-input-row" data-value="0">
                    <input value="1" min="1" max="10" step="1" type="range" placeholder="" id="range" name="range"
                           class="u-input u-range">
                    <input type="number" placeholder="" id="guest" name="guest" class="u-input u-input-rectangle u-text-black"
                           min="1" max="20" step="1" value="1">
                </div>
            </div>
            <div class="u-form-group u-form-group-4">
                <label for="comment" class="u-label">Comments (if any)</label>
                <input type="text" placeholder="" id="comment" name="comment" class="u-input u-input-rectangle">
            </div>
            <div class="u-align-left u-form-group u-form-submit">
                <input type="submit" value="Confirm Booking" class="u-btn u-btn-submit u-button-style" name="confirmBooking">
            </div>
        </form>
        </div>
    </div>
</section>
</body>
</html>

