import { h, FunctionalComponent } from "preact";
import { TabBar } from "./components/TopBar";
import "./styles.css"

export const LandingPage: FunctionalComponent = () => {
    
    return (
        <div>
            <body>
            
                <TabBar/>

                <div class="content">
                    <h1>Welcome to the Application</h1>
                    <h2>Select an Option Below</h2>
                    <div class="options">
                    <div class="option left">
                        <img src="https://via.placeholder.com/60" alt="Option 1"></img>
                        <p>Option 1</p>
                    </div>
                    <div class="option right">
                        <img src="https://via.placeholder.com/60" alt="Option 2"></img>
                        <p>Option 2</p>
                    </div>
                    </div>
                </div>

            </body>
        </div>
    )
}