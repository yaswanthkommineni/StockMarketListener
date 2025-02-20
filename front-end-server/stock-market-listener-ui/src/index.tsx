import { h, render } from 'preact';
import {LandingPage} from './landingPage/LandingPage'

const App = () => <h1><LandingPage/></h1>;

const root = document.getElementById('app');
if (root) {
  render(<App />, root);
}