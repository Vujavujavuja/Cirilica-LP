import os
from pythonParser.parser import parse
import re


def numeric_sort_key(filename):
    numbers = re.findall(r'\d+', filename)
    return (int(numbers[0]) if numbers else 0, filename)


def list_files(directory):
    return sorted(
        [f for f in os.listdir(directory) if os.path.isfile(os.path.join(directory, f))],
        key=numeric_sort_key
    )


def read_file(directory, filename):
    with open(os.path.join(directory, filename), 'r', encoding='utf-8') as file:
        return file.read()


def main():
    directory = "../javaLexer/ProgramskiPrevodioci1/src/codes"

    files = list_files(directory)
    for i, file in enumerate(files):
        print(f"{i + 1}. {file}")

    choice = int(input("Enter the number of the file to parse: "))
    selected_file = files[choice - 1]

    file_content = read_file(directory, selected_file)
    print(f"\nContents of {selected_file}:\n{file_content}")

    result = parse(file_content)
    print("\nParser Output:")
    print(result)


if __name__ == "__main__":
    main()
