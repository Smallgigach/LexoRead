import SignButton from "../../shared/SignInUpButton/SignInUpButton"
import { SignInput } from "../../shared/SignInput/SignInput"
import cl from './styles/SignInUpPage.module.css'
import arrowPage from '../../assets/img/SignInUp/arrow.png'
import { useForm, type SubmitErrorHandler, type SubmitHandler } from "react-hook-form"
interface SignUpForm {
    username: string,
    email: string, 
    password: string,
    confirmPassword: string,
    error?: any
}

 export  default function SignUpPage() {
    const {handleSubmit, register, watch,formState: {errors}} = useForm<SignUpForm>();
    const password = watch("password")
    const onSubmit: SubmitHandler<SignUpForm> = (data) => {
        console.log(data);
    }
    const error: SubmitErrorHandler<SignUpForm> = (data) => {
        console.log(data);
        
    }
    return (
        <>
        <div className={cl['sign__background']} >
            <div className={cl["sign__block"]}> 
                <a href="/auth" className={cl["arrow"]}>
                <img src={arrowPage} alt="arrow" /></a>           
                <h1 className={cl["sign__title"]}>LexoRead</h1>
                <h2 className={cl["sign_subTitle"]}>Зарегистрируйтесь в <br />LexoRead</h2>
                <form onSubmit={handleSubmit(onSubmit, error)} noValidate>
                    
                <SignInput type="text" placeholder="Ваш Логин" {...register('username', {
                    required: true,
                    minLength: {value: 8, message: "Логин должен быть не менее 8 символов"},

                }) }/>
                <span className={cl['error']}>{errors.username?.message}</span>
                 <SignInput type="email" placeholder="Введите почту" {...register('email', {
                    required: true,
                    pattern: {
                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                    message: "Некорректный email"
                    }
                 })}/>
                 <span className={cl['error']}>{errors.email?.message}</span>
                  <SignInput type="password" placeholder="Пароль" {...register('password', {
                    required: true,
                    minLength: {
                        value: 10, message: "Пароль должен быть не менее 10 символов"
                    },  
                  })}/>
                  <span className={cl['error']}>{errors.password?.message}</span>
                   <SignInput type="password" placeholder="Повторите пароль" {...register('confirmPassword', {
                    required: true,
                     minLength: {
                        value: 10, message: "Пароль должен быть не менее 10 символов",
                    }, 
                    validate: (value) => {
                        return value == password || "пароли не совпадают"
                    }
                    
                   })}/>
                   <span className={cl['error']}>{errors.confirmPassword?.message}</span>         
                <SignButton >Зарегистрироваться</SignButton>
                </form>
            </div>
        </div>
        </>
    )
}
