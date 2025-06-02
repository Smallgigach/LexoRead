import cl from '../AuthPage/styles/SignInUpPage.module.css'
import { SignInput } from '../../shared/SignInput/SignInput'
import SignButton from '../../shared/SignInUpButton/SignInUpButton'
import { Link } from 'react-router'
import googleImg from '../../assets/img/SignInUp/OAuth/google.png'
import vkImg from '../../assets/img/SignInUp/OAuth/vk.png'
import github from '../../assets/img/SignInUp/OAuth/github.png'
export default function SignInPage() {
    return (
        <>
        <div className={cl['sign__background']} >
            <div className={cl["sign__block"]}>             
                <h1 className={cl["sign__title"]}>LexoRead</h1>
                <h2 className={cl["sign_subTitle"]}>Войдите в LexoRead</h2>
               <SignInput type='text' placeholder='Ваш логин'/>
                <SignInput type='password' placeholder='Пароль'/>
                <SignButton>Войти</SignButton>
                <span>или</span>
                <Link to={'/reg'} className={cl['sign__link']}>Зарегистрироваться</Link>
                <span className={cl['sign_subTitle_2']}>Войти с помощью</span>
                <div className={cl['sign_OAuth']}>
                    <a href="#">
                        <img src={googleImg} alt="google" />
                    </a>
                    <a href="">
                        <img src={vkImg} alt="" />
                    </a>
                    <a href="#">
                        <img src={github} alt="" />
                    </a>
                </div>
            </div>
        </div>
        </>
    )
}