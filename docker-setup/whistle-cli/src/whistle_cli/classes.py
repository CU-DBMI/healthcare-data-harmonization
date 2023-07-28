import subprocess
from dataclasses import dataclass
from pathlib import Path
from subprocess import Popen


@dataclass
class WhistleLauncher:
    input_file_path: Path
    output_dir_path: Path
    main_file_path: Path
    mapping_functions_dir_path: Path
    mapping_concept_maps_dir_name: Path
    binary_name: str

    def convert(self):
        if not (self.binary_name and self.input_file_path and self.main_file_path):
            raise ValueError(f'One or more required options are missing: binary name: "{self.binary_name}", '
                             f'input file: "{self.input_file_path}", main entry file: "{self.main_file_path}"')

        args = [self.binary_name,
                '-input_file_spec',
                self.input_file_path,
                '-mapping_file_spec',
                self.main_file_path
                ]
        if self.mapping_functions_dir_path:
            args.extend(['-lib_dir_spec', self.mapping_functions_dir_path])

        if self.output_dir_path:
            args.extend(['-output_dir', self.output_dir_path])

        print(f'Command: "{args}"')
        whistle = Popen(args,
                        stdout=subprocess.PIPE,
                        stderr=subprocess.PIPE)
        exit_code = whistle.wait()
        print(f'whistle.pid: {whistle.pid}  and exit code: {exit_code}')

        print(f'\nSTDOUT is: {whistle.stdout}')
        for line in whistle.stdout:
            print(line)
        print(f'\nSTDERR is: {whistle.stderr}')
        for line in whistle.stderr:
            print(line)


