import cl from '../SignInput/SignInput.module.css'
type SignInputProps = {
placeholder: string
type: string
}
export function SignInput({placeholder, type}: SignInputProps) {
    return (
        <>
        <input type={type} placeholder={placeholder} className={cl["input"]} required/>
        </>
    )
}