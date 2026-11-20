#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source_dir="$script_dir/src/main/java"
main_class="ca.ucalgary.ensf480.flightreservation.App"
build_dir="$(mktemp -d /tmp/flight-ui-classes.XXXXXX)"

cleanup() {
    rm -rf -- "$build_dir"
}
trap cleanup EXIT

mapfile -d '' java_sources < <(find "$source_dir" -type f -name '*.java' -print0)

if (( ${#java_sources[@]} == 0 )); then
    echo "No Java source files found under: $source_dir" >&2
    exit 1
fi

echo "Compiling ${#java_sources[@]} Java source file(s)..."
javac -Xlint:all -d "$build_dir" "${java_sources[@]}"

if [[ "${1:-}" == "--compile-only" ]]; then
    echo "Compilation completed successfully."
    exit 0
fi

if (( $# > 0 )); then
    echo "Usage: $0 [--compile-only]" >&2
    exit 2
fi

echo "Starting Flight Reservation UI..."
java -cp "$build_dir" "$main_class"
