const First = () => {
  return <h3>1번 자식 컴포넌트</h3>;
};

const Second = ({ name }) => {
  return <h3>{name} 자식 컴포넌트</h3>;
};

const Parent = ({ children, color, name }) => {
  //{color:color} == {color}
  const style = {
    border: "4px solid yellow",
    padding: "20px",
    margin: "10px",
    borderRadius: "10px",
  };
  return (
    <div style={style}>
      <p style={{ color }}> Parent 가 보여줄 컴포넌트 영역!! {name}</p>
      <hr />
      {children}
    </div>
  );
};

const ChildrenExam = () => {
  return (
    <div>
      <h2>Props.Children</h2>
      <Parent color="green" name="test" a="1122">
        <First />
        <Second name="kang" />
        <Second name="kim" />
        <Second name="hong" />
      </Parent>
    </div>
  );
};

export default ChildrenExam;
