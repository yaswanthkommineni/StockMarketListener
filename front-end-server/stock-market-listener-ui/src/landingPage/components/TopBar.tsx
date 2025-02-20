import '../styles.css';
import { h, FunctionalComponent } from "preact";

export const TabBar: FunctionalComponent = () => {
    
    return (
        <div class="top-bar">
            <div class="app-name">NSE Stock Alerts</div>
            <div class="profile-section">
            <img src="../../images/profileLogo.jpg" width="100" height="100"></img>
            <button>Logout</button>
            </div>
        </div> 
    )
}