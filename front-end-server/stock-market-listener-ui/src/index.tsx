import { h, render } from 'preact';

const App = () => <h1>Hello, Preact with TypeScript!</h1>;

const root = document.getElementById('app');
if (root) {
  render(<App />, root);
}