import SignButton from "../../shared/SignInUpButton/SignInUpButton"
import { SignInput } from "../../shared/SignInput/SignInput"
import cl from './styles/SignInUpPage.module.css'
import arrowPage from '../../assets/img/SignInUp/arrow.png'
 export  default function SignUpPage() {
    return (
        <>
        <div className={cl['sign__background']} >
            <div className={cl["sign__block"]}> 
                <a href="/auth" className={cl["arrow"]}>
                <img src={arrowPage} alt="arrow" /></a>           
                <h1 className={cl["sign__title"]}>LexoRead</h1>
                <h2 className={cl["sign_subTitle"]}>Зарегистрируйтесь в <br />LexoRead</h2>
                <SignInput type="text" placeholder="Ваш Логин"/>
                 <SignInput type="email" placeholder="Введите почту"/>
                  <SignInput type="password" placeholder="Пароль"/>
                   <SignInput type="password" placeholder="Повторите пароль"/>         
                <SignButton >Зарегистрироваться</SignButton>
                
            </div>
        </div>
        </>
    )
}
