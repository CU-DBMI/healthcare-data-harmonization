import typing as t
from pathlib import Path

import typer
from typer import Typer

from whistle_cli import WhistleLauncher

cli = Typer()


@cli.command()
def convert(
        input_path: t.Annotated[Path, typer.Option()] = Path('/whistle/input/*.json'),
        output_dir: t.Annotated[Path, typer.Option()] = Path('/whistle/output'),
        main_file: t.Annotated[Path, typer.Option()] = Path('/whistle/mains/main.wstl'),
        mapping_functions: t.Annotated[Path, typer.Option()] = Path('/whistle/mapping-functions'),
        mapping_concept_maps: t.Annotated[Path, typer.Option()] = Path('/whistle/mapping-concept-maps'),

        binary_name: t.Annotated[str, typer.Option()] = 'whistle',
):
    """Runs the Whistle binary with configurable parameters."""
    print("Launching whistle")
    whistle_launcher: WhistleLauncher = WhistleLauncher(
        input_file_path=input_path,
        output_dir_path=output_dir,
        main_file_path=main_file,
        mapping_functions_dir_path=mapping_functions,
        mapping_concept_maps_dir_name=mapping_concept_maps,
        binary_name=binary_name,
    )
    whistle_launcher.convert()


@cli.command(hidden=True)
def debug():
    print("debugging")


cli()
