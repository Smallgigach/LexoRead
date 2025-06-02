  import SignUpPage from './pages/AuthPage/SignUpPage'
  import SignInPage  from './pages/AuthPage/SignInPage';
  import { Route, Routes } from 'react-router-dom'
function App() {

  return (
    
    <Routes>
      <Route path='/auth' index element={<SignInPage/>}/>
      <Route path='/reg' index element={<SignUpPage/>}/>
    </Routes>
  );
}

export default App
