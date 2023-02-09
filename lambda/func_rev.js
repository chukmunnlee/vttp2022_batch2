const name = "fred"
const contact = { 
    name: "fred",
    email: "fred@gmail.com"
}
const val = 3
const square = (x) => {
    return x * x
}

const apply = (f, x) => {
    console.info(`f ${x} `, f(x))
}

console.info('name: ', name)
console.info('contact: ', contact)
console.info('square 3 ', square(val))
console.info('square ', square)
apply(square, val)
apply(
    (x) => {
        console.info(`Hello ${x}`)
    },
)