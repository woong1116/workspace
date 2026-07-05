import { useNavigate } from 'react-router-dom';

const MyComponent = () => {

    const nav = useNavigate();

    const goHome = () => {
        nav('/home');
    }
    const goBack = () => {
        nav(-1);
    }
    const goForward = () => {
        nav(1);
    }
    const replaceHome = () => {
        nav('/home', { replace: true });
    }

    return (
        <div>
        <button type="button" onClick={goHome}>홈으로 이동</button>
        <button type="button" onClick={goBack}>뒤로 가기</button>
        <button type="button" onClick={goForward}>앞으로 가기</button>
        <button type="button" onClick={replaceHome}>홈으로 교체 이동</button>
        </div>
    );
}

export default MyComponent; 