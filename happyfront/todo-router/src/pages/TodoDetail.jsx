import { useParams } from 'react-router-dom';

const TodoDetail = () => {
    const { id } = useParams(); // URL에서 id 파라미터 가져오기
    console.log("TodoDetail id:", id); // id 값 확인

  return (<div>
    <h1>Todo Detail</h1>
    <p>Todo ID: {id}</p>
  </div>
  );
};

export default TodoDetail;