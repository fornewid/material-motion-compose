#!/bin/bash
set -ex

# Generate API docs
./gradlew dokkaHtmlMultiModule --stacktrace

# Copy *.md files into docs directory
cp README.md docs/index.md
cp docs/header.png docs/docs/header.png
