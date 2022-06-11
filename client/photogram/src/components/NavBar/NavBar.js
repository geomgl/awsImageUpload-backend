import React from "react";
import "./NavBar.css";

function NavBar() {
  return (
    <div id="navBarContainer">
      <div id="navBar" className="flex">
        <div id="navBarLogo"> Photogram </div>
        <div id="searchBarContainer">
          <input id="searchBar" placeholder="Search"></input>
        </div>
        <div id="navBarIconContainer" className="flex">
          <div>
            <i className="bi-house navBarIcon"> </i>
          </div>
          <div>
            <i className="bi-inbox-fill navBarIcon"> </i>
          </div>
          <div>
            <i className="bi-plus-square navBarIcon"> </i>
          </div>
          <div>
            <i className="bi-compass navBarIcon"> </i>
          </div>
          <div>
            <i className="bi-heart navBarIcon"> </i>
          </div>
        </div>
      </div>
    </div>
  );
}

export default NavBar;
