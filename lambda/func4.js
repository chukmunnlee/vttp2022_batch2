var power = function(x, n) {
    var result = 1;
    for (var i = 0; i < n; i++)
        result *= x
    return result
}

console.info('power of 2^3: ', power(2, 3))
console.info('power of 4^3: ', power(4, 3))

var mkPower = function(n) {
    // inner 
    return function(x) {
        var result = 1;
        for (var i = 0; i < n; i++)
            result *= x
        return result
    }
}

var square = mkPower(2)
var cube = mkPower(3)

console.info('4^2: ', square(4))
console.info('5^2: ', square(5))
console.info('5^3: ', cube(5))