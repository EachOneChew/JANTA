# Team 110:
- Logan Maier
- Yichuan Qiu
- Ivan Shan
- Yixin Li

# Table of Contents

1. [Introduction](#1)
2. [Instructions](#2)
3. [Major Project Stages](#3)
4. [Sprint Releases](#4)
5. [Ackowledgements](#5)

# JANTA: Just Another Note Taking App<a name="1"/>

JANTA is a full featured, note taking app targeted towards students taking notes for various academic curricula. Its features include Rich Text editing, autosave, and an awesome aesthetic (thanks to Yixin ^_^). Also included in JANTA is an intuitive annotation and labeling system designed to allow users to structure and navigate notes in a manner uniquely suited to textbook-based content. JANTA is primarily written in Kotlin, but works with HTML under the hood using a WebEngine, allowing it to cleanly handle copy & pasting from webpages and other HTML editors.

<img src="Images/sc_joined.jpg" width="900"/>

# Instructions<a name="2"/>

JANTA's interface should be fairly self explanatory. Regardless, below is a quick all-you-need-to-know.

<img src="Images/sc_expanded.png" width="900"/>

The sidebar buttons, in order:

1. Toggle note list<sup>1</sup>
2. Save current note<sup>2</sup>
3. Create new note
4. Toggle label list
5. Toggle theme

<sup>1</sup> Notes can be renamed and deleted by right clicking on them in the note list.

<sup>2</sup> JANTA saves the content in the current note any time the user switches a note. Saving is also triggered by content commits; virtually any action taken in editor including clicking, applying a font, indenting, etc. Thus, the only time where the user should manually save is when they intend to close the app immediately after typing a large amount of text.

## Shortcuts

| Action | Shortcut |
| ---      | ---      |
| **Bold** | Ctrl + B |
| *Italic* | Ctrl + I |
| <ins>Underline</ins> | Ctrl + U |
| Annotate | Ctrl + P |
| Label target | Ctrl + L |
| Label reference | Ctrl + O |
| Select all | Ctrl + A |
| Redo | Ctrl + Y or Ctrl + Shift + Z |
| Undo | Ctrl + Z |
| <h1>Heading 1</h1> | Shift + Alt + 1 |
| <h2>Heading 2</h2> | Shift + Alt + 2 |
| <h3>Heading 3</h3> | Shift + Alt + 3 |
| <h4>Heading 4</h4> | Shift + Alt + 4 |
| <h5>Heading 5</h5> | Shift + Alt + 5 |
| <h6>Heading 6</h6> | Shift + Alt + 6 |
| Paragraph | Shift + Alt + 7 |
| Open help dialog | Alt + 0 |
| Find | Ctrl + F |

# Major Project Stages<a name="3"/>

- [Introduction](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Introduction)
- [Requirements](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Requirements)
- [Analysis-&-Design](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Analysis-&-Design)
- [Implementation](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Implementation)
- [Testing](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Testing)

# Sprint Releases<a name="4"/>

- [Sprint 1](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Sprint-1)
- [Sprint 2](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Sprint-2)
- [Sprint 3](https://gitlab.uwaterloo.ca/y2679li/cs398-project/-/wikis/Sprint-3)

# Ackowledgements<a name="5"/>

- [TinyMCE](https://github.com/tinymce/tinymce)
- [Stackoverflow](https://stackoverflow.com/)
