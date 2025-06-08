import cl from '../AuthPage/styles/SignInUpPage.module.css'
import { SignInput } from '../../shared/SignInput/SignInput'
import SignButton from '../../shared/SignInUpButton/SignInUpButton'
import { data, Link } from 'react-router'
import googleImg from '../../assets/img/SignInUp/OAuth/google.png'
import vkImg from '../../assets/img/SignInUp/OAuth/vk.png'
import github from '../../assets/img/SignInUp/OAuth/github.png'
import { useForm, type SubmitErrorHandler, type SubmitHandler } from 'react-hook-form'
interface SignInForm {
    username: string,
    password: string,
}
export default function SignInPage() {
    const {handleSubmit, register, formState: {errors}} = useForm<SignInForm>();
    const onSubmit: SubmitHandler<SignInForm> = (data) => {
        console.log(data);
    }
    const Error: SubmitErrorHandler<SignInForm> = (data) => {
        console.log(data);
    }
    return (
        <>
        
        <div className={cl['sign__background']} >
            <div className={cl["sign__block"]}>             
                <h1 className={cl["sign__title"]}>LexoRead</h1>
                <h2 className={cl["sign_subTitle"]}>Войдите в LexoRead</h2>
                <form noValidate onSubmit={handleSubmit(onSubmit)}
                 className={cl['sign__form']}>
               <SignInput type='text' placeholder='Ваш логин' {...register('username', {
                required: true,
                    minLength: {value: 8, message: "Логин должен быть не менее 8 символов"},
               })}/>
               <span className={cl['error']}>{errors.username?.message}</span>
                <SignInput type='password' placeholder='Пароль' {...register('password', {
                    required: true,
                     minLength: {
                        value: 10, message: "Пароль должен быть не менее 10 символов",
                    }, 
                })}/> 
                <span className={cl['error']}>{errors.password?.message}</span>
                {/* доработать,связать с бд */}

                <SignButton>Войти</SignButton>
                <span className={cl['or']}>или</span>
                <Link to={'/reg'} className={cl['sign__link']}>Зарегистрироваться</Link>
                <span className={cl['sign_subTitle_2']}>Войти с помощью</span>
                </form>
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