var add = function(a, b) {
    return a + b
}
var mul = function(a, b) {
    return a * b
}
var sub = function(a, b) {
    return a - b
}
var div = function(a, b) {
    return a / b
}

var apply = function(f, v0, v1) {
    return f(v0, v1)
}

console.info('add ', apply(add, 5, 10))
console.info('mul ', apply(mul, 5, 10))
console.info('div ', apply(mul, 5, 10))
console.info('sub ', apply(mul, 5, 10))

var o = [ add, 5, 10, mul, 10, 20 ]
console.info('calculate: ', apply(o[0], o[1], o[2]))
console.info('calculate: ', apply(o[3], o[4], o[5]))