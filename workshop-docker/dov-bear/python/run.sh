#!/usr/bin/env bash
gunicorn -b ":${PORT:-3000}" --log-level debug main:app 
