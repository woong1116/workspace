function Welcome({ name }) {
  return <h2>안녕하세요, {name}님!</h2>;
}

function App() {
  return (
    <div>
      <Welcome name="김철수" />
      <Welcome name="이영희" />
      <Welcome name="박민수" />
    </div>
  );
}

export default Welcome;