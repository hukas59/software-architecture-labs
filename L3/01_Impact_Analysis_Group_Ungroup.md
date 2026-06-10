| Package name | # of classes | Comments |
|--------------|:------------:|----------|
| `org.jhotdraw.draw.action` | 3 | Entry point of the change request — visited first and marked **CHANGED**. `GroupAction`, `UngroupAction` and their base `AbstractSelectedAction` start the feature from the *Group/Ungroup Selection* menu items. |
| `org.jhotdraw.draw` | 10 | Core model/view of the feature (**CHANGED / PROPAGATES**). Grouping moves figures between the drawing and a group: `Figure`, `AbstractFigure`, `CompositeFigure`, `AbstractCompositeFigure`, `GroupFigure`, `Drawing`, `AbstractDrawing`, `DefaultDrawing`, `DrawingView`, `DrawingEditor`. |
| `org.jhotdraw.draw.event` | 7 | Change/selection notifications fired while children move in or out of the group (**PROPAGATES** — listeners must stay consistent): `FigureEvent`, `FigureListener`, `FigureAdapter`, `CompositeFigureEvent`, `CompositeFigureListener`, `FigureSelectionEvent`, `FigureSelectionListener`. |
| `org.jhotdraw.draw.handle` | 3 | Selection handles rebuilt when the group becomes the selected figure (**PROPAGATES**): `Handle`, `BoundsOutlineHandle`, `TransformHandleKit`. |
| `org.jhotdraw.draw.layouter` | 1 | `Layouter` — optional auto-layout a composite figure can apply to its children after grouping. Visited, marked **UNCHANGED**. |
| `org.jhotdraw.geom` | 2 | Geometry for the group's combined bounds: `Geom`, `Dimension2DDouble`. Visited as neighbours, **UNCHANGED**. |
| `org.jhotdraw.xml` | 3 | Persistence so a saved group reloads with its children (**PROPAGATES**): `DOMStorable`, `DOMInput`, `DOMOutput`. |
| `org.jhotdraw.util` | 2 | Support used by the feature: `ResourceBundleUtil` (action labels) and `ReversedList` (child iteration order). **UNCHANGED**. |
| `org.jhotdraw.app` | 1 | `Disposable` — the lifecycle contract the action implements. **UNCHANGED**. |
| `org.jhotdraw.beans` | 1 | `WeakPropertyChangeListener` — lets the action track the active view without leaking memory. **UNCHANGED**. |
| `javax.swing.undo` *(Java SE)* | 2 | `UndoableEdit`, `AbstractUndoableEdit` — undo/redo of the group/ungroup. Visited but outside the project, so **UNCHANGED**. |

**Estimated Impact Set total:** 35 classes visited across 11 packages (10 JHotDraw
packages + 1 Java SE). The classes most likely to be modified for the change are
concentrated in `org.jhotdraw.draw.action` and `org.jhotdraw.draw`; the remaining
packages were visited as neighbours and judged UNCHANGED or only PROPAGATES.
