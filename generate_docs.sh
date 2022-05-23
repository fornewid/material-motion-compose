#!/bin/bash
set -e

# Generate API docs
./gradlew dokkaHtmlMultiModule

# Copy *.md files into docs directory
cp README.md docs/index.md
