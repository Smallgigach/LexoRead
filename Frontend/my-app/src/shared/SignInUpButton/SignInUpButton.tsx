import cl from "../SignInUpButton/SignButton.module.css"
type SignInUpProps =  {
    children?: React.ReactNode
}
export default function SignButton({children}: SignInUpProps)  {
return (    
<>
<button  className={cl['button']}  onClick={() => console.log('clicked')
}>
    <span>{children}</span>
</button>
    </>
)
}
