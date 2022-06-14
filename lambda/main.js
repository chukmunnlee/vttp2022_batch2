/*
function greetings(name) {
    console.info(`Hello ${name}`)
} */
var fred = "fred"
var greetings = function (name) {
    console.info(`Hello ${name}`)
}

console.info(fred)
console.info('greetings: ', greetings)
greetings(fred)

var hello = greetings
console.info('hello: ', hello)
hello('betty')
