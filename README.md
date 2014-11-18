xml-repair
==========

xml tag repair tool

Running XMLRepair in the command line will read xml tags from stdin and include missed tags or move misplaced tags to the correct location.
For example, running "java XMLRepair <<< "<a><b></b><b><c></c></b></a>"

will print:

8
<a>
  <b></b>
  <b>
    <c></c>
  </b>
</a>
