#!/usr/bin/env bash
export CLASSPATH="./target/classes"

java vttp2022.shoppingcart.server.Main ../../carts 3000;
