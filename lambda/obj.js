const greet = {
    name: "fred",
    hello: function() {
        console.info('hello ', this.name);
    }
}

const name = 'barney'

greet.hello()

const f = greet.hello

f()