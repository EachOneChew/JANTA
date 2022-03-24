window.initOptions = {
    skin: "oxide",
    content_css: "default",
};

window.annotationInstances = null;
window.labeltargetInstances = null;
window.labelTypeVar = null;

const clearAnnotationTippys = function () {
    if (window.annotationInstances) {
        window.annotationInstances.forEach((e) => e.destroy());
    }
    window.annotationInstances = null;
};

const setAnnotationTippys = function () {
    const doc = document.querySelector("iframe").contentDocument;
    window.annotationInstances = tippy(
        doc.querySelectorAll(".annotation_class"),
        {
            arrow: false,
            placement: "right",
            offset: [40, 10],
            theme: "light-border",
        }
    );
};

const clearLabeltargetTippys = function () {
    if (window.labeltargetInstances) {
        window.labeltargetInstances.forEach((e) => e.destroy());
    }
    window.labeltargetInstances = null;
};

const setLabeltargetTippys = function () {
    const doc = document.querySelector("iframe").contentDocument;
    window.labeltargetInstances = tippy(
        doc.querySelectorAll(".labeltarget_class"),
        {
            arrow: false,
            placement: "right",
            offset: [40, 10],
            theme: "light-border",
        }
    );
};

window.destroyFunction = function () {
    const bridge = window.bridgeObj;

    clearAnnotationTippys();
    clearLabeltargetTippys();

    if (tinymce.activeEditor) {
        tinymce.activeEditor.destroy();
    }

    bridge.setObjs(null);
    bridge.setInterfaceContent("");
};

window.initFunction = function (initContent) {
    window.destroyFunction();

    tinymce.init({
        selector: "#TinyMCEInstance",
        branding: false,
        menubar: false,
        resize: false,
        height: "100vh",

        skin: window.initOptions.skin,
        content_css: [window.initOptions.content_css],

        plugins: [
            "help",
            "code",
            "lists",
            "advlist",
            "table",
            "searchreplace",
            "wordcount",
            "hr",
        ],

        custom_elements: "~annotation",
        valid_children: "-annotation[annotation|a],-a[annotation|a]",

        formats: {
            annotation: {
                inline: "annotation",
                attributes: {
                    class: "annotation_class",
                    "data-tippy-content": "%value",
                },
                styles: {
                    "background-color": "#FFFF00",
                },
                exact: true,
                merge_siblings: false,
            },
            labeltarget: {
                inline: "a",
                attributes: {
                    class: "labeltarget_class",
                    "data-tippy-content": "%value",
                    name: "%value",
                    type: "%value2",
                },
                styles: {
                    "background-color": "#FF8C00",
                },
                exact: true,
                merge_siblings: false,
            },
            labelref: {
                inline: "a",
                attributes: {
                    class: "labelref_class",
                    href: "%value",
                },
                styles: {
                    "background-color": "#7FFFD4",
                },
                exact: true,
                merge_siblings: false,
            },
        },

        toolbar: `
            undo redo |
            styleselect |
            fontsizeselectgroup hr addAnnotationButton labelgroup |
            bold italic underline strikethrough |
            indentgroup aligngroup listgroup tablegroup |
            searchreplace code wordcount help |
        `,

        toolbar_groups: {
            fontsizeselectgroup: {
                icon: "change-case",
                tooltip: "Font size",
                items: "fontsizeselect",
            },
            labelgroup: {
                icon: "bookmark",
                tooltip: "Label",
                items: "addLabeltargetButton addLabelrefButton",
            },
            indentgroup: {
                icon: "indent",
                tooltip: "Indentation",
                items: "indent outdent",
            },
            aligngroup: {
                icon: "align-left",
                tooltip: "Alignment",
                items: "alignleft aligncenter alignright alignjustify",
            },
            listgroup: {
                icon: "unordered-list",
                tooltip: "Lists",
                items: "bullist numlist",
            },
            tablegroup: {
                icon: "table",
                tooltip: "Tables",
                items: `
                    table tabledelete |
                    tableprops tablerowprops tablecellprops |
                    tableinsertrowbefore tableinsertrowafter tabledeleterow |
                    tableinsertcolbefore tableinsertcolafter tabledeletecol |
                `,
            },
        },

        help_tabs: [
            "shortcuts",
            {
                name: "annotations_tab",
                title: "Annotations",
                items: [
                    {
                        type: "htmlpanel",
                        html: `
                            <p>Selections may be annotated with a short text. The <span style="background-color: #FFFF00">annotation</span> will then be displayed when the user hovers over the affected selection.</p>
                            <p>Deletion and editing of <span style="background-color: #FFFF00">annotations</span> is done through the context form triggered by moving the caret into their range.</p>
                        `,
                    },
                ],
            },
            {
                name: "labels_tab",
                title: "Labels",
                items: [
                    {
                        type: "htmlpanel",
                        html: `
                            <p>Within a note, selections may be designated label <span style="background-color: #FF8C00">targets</span> and label <span style="background-color: #7FFFD4">references</span>. Label <span style="background-color: #7FFFD4">references</span> point to label <span style="background-color: #FF8C00">targets</span>.</p>
                            <p>Note that <span style="background-color: #7FFFD4">references</span> are not allowed to point to <span style="background-color: #FF8C00">targets</span> that do not exist. <span style="background-color: ##FF8C00">Target</span> uniqueness is enforced by the editor.</p>
                            <p>Label <span style="background-color: #FF8C00">targets</span> are given a type, then a name by the user on creation through two successive context forms. <span style="background-color: #7FFFD4">references</span> are pointed to <span style="background-color: #FF8C00">targets</span> using names.</p>
                            <p>To see the name of a <span style="background-color: ##FF8C00">target</span>, hover over its selection.</p>
                            <p>There is a tab in the sidebar which allows users to aggregate labels within a note by type.</p>
                            <p>Deleting a <span style="background-color: ##FF8C00">target</span> will delete all <span style="background-color: #7FFFD4">references</span> pointing to it. Deleting a <span style="background-color: #7FFFD4">reference</span> will not affect the <span style="background-color: ##FF8C00">target</span>.</p>
                            <p>Deletion and editing of labels is done through the context form triggered by moving the caret into their range.</p>
                        `,
                    },
                ],
            },
        ],

        setup: function (ed) {
            const bridge = window.bridgeObj;

            const openForm = function (target) {
                if (ed.selection.getContent()) {
                    ed.fire("contexttoolbar-show", {
                        toolbarKey: target,
                    });
                }
            };

            const isRoot = function (node, target) {
                return node ? node.className === target : false;
            };

            const getRoot = function (node, target) {
                while (node && !isRoot(node, target)) {
                    node = node.parentNode;
                }
                return node;
            };

            const calcAnnotationPredicate = function (node) {
                return getRoot(node, "annotation_class") ? true : false;
            };

            const calcLabeltargetPredicate = function (node) {
                return getRoot(node, "labeltarget_class") ? true : false;
            };

            const calcLabelrefPredicate = function (node) {
                return getRoot(node, "labelref_class") ? true : false;
            };

            ed.on("init", function () {
                // triggers SetContent event and updates interface content
                ed.setContent(initContent);
                bridge.setObjs(ed);
                setAnnotationTippys();
                setLabeltargetTippys();
            });

            ed.on("Change SetContent", function (_) {
                bridge.setInterfaceContent(ed.getContent());
            });

            ed.ui.registry.addButton("addAnnotationButton", {
                icon: "edit-block",
                tooltip: "Annotate",
                onAction: function (_) {
                    openForm("annotate-form");
                },
            });

            ed.ui.registry.addButton("addLabeltargetButton", {
                icon: "bookmark",
                tooltip: "Label target",
                onAction: function (_) {
                    openForm("labeltarget-form");
                },
            });

            ed.ui.registry.addButton("addLabelrefButton", {
                icon: "link",
                tooltip: "Label reference",
                onAction: function (_) {
                    openForm("labelref-form");
                },
            });

            ed.ui.registry.addContextForm("annotate-form", {
                label: "Annotate",
                initValue: function () {
                    var root = getRoot(
                        ed.selection.getNode(),
                        "annotation_class"
                    );
                    return root ? root.getAttribute("data-tippy-content") : "";
                },
                position: "selection",
                predicate: calcAnnotationPredicate,
                commands: [
                    {
                        type: "contextformbutton",
                        icon: "checkmark",
                        tooltip: "Add annotation",
                        primary: true,
                        onAction: function (formApi) {
                            var value = formApi.getValue();
                            if (value) {
                                var root = getRoot(
                                    ed.selection.getNode(),
                                    "annotation_class"
                                );
                                if (!root) {
                                    for (k in ed.formatter.get()) {
                                        ed.formatter.remove(k);
                                    }
                                    ed.formatter.apply("annotation", {
                                        value: value,
                                    });
                                } else {
                                    root.setAttribute(
                                        "data-tippy-content",
                                        value
                                    );
                                }
                            }
                            ed.fire("Change");
                            clearAnnotationTippys();
                            setAnnotationTippys();
                            formApi.hide();
                        },
                    },
                    {
                        type: "contextformbutton",
                        icon: "close",
                        tooltip: "Remove annotation",
                        onAction: function (formApi) {
                            var root = getRoot(
                                ed.selection.getNode(),
                                "annotation_class"
                            );
                            if (root) {
                                root.removeAttribute("data-tippy-content");
                                root.removeAttribute("style");
                                ed.formatter.remove("annotation", null, root);
                            }
                            ed.fire("Change");
                            clearAnnotationTippys();
                            setAnnotationTippys();
                            formApi.hide();
                        },
                    },
                ],
            });

            ed.ui.registry.addContextForm("labeltarget-form", {
                label: "Label target",
                initValue: function () {
                    return "";
                },
                position: "selection",
                predicate: calcLabeltargetPredicate,
                commands: [
                    {
                        type: "contextformbutton",
                        icon: "checkmark",
                        tooltip: "Add target type",
                        primary: true,
                        onSetup: function (buttonApi) {
                            var root = getRoot(
                                ed.selection.getNode(),
                                "labeltarget_class"
                            );
                            buttonApi.setDisabled(!!root);
                        },
                        onAction: function (formApi) {
                            var value = formApi.getValue();
                            if (value) {
                                window.labelTypeVar = value;
                                ed.fire("contexttoolbar-show", {
                                    toolbarKey: "labeltarget-form2",
                                });
                            }
                            formApi.hide();
                        },
                    },
                    {
                        type: "contextformbutton",
                        icon: "close",
                        tooltip: "Remove target",
                        onSetup: function (buttonApi) {
                            var root = getRoot(
                                ed.selection.getNode(),
                                "labeltarget_class"
                            );
                            buttonApi.setDisabled(!root);
                        },
                        onAction: function (formApi) {
                            var root = getRoot(
                                ed.selection.getNode(),
                                "labeltarget_class"
                            );
                            if (root) {
                                const temp = root.getAttribute("name");
                                root.removeAttribute("data-tippy-content");
                                root.removeAttribute("name");
                                root.removeAttribute("type");
                                root.removeAttribute("style");
                                ed.formatter.remove("labeltarget", null, root);
                                const doc =
                                    document.querySelector(
                                        "iframe"
                                    ).contentDocument;
                                doc.querySelectorAll(
                                    `a[href="#${temp}"]`
                                ).forEach((e) => {
                                    e.removeAttribute("href");
                                    e.removeAttribute("style");
                                    ed.formatter.remove("labelref", null, e);
                                });
                                // CALL MODEL DELETE
                                // callModel(...)
                            }
                            ed.fire("Change");
                            clearLabeltargetTippys();
                            setLabeltargetTippys();
                            formApi.hide();
                        },
                    },
                ],
            });

            ed.ui.registry.addContextForm("labeltarget-form2", {
                label: "Label target",
                initValue: function () {
                    return "";
                },
                position: "selection",
                commands: [
                    {
                        type: "contextformbutton",
                        icon: "checkmark",
                        tooltip: "Add target name",
                        primary: true,
                        onAction: function (formApi) {
                            var value = formApi.getValue();
                            if (value) {
                                var root = getRoot(
                                    ed.selection.getNode(),
                                    "labeltarget_class"
                                );
                                const doc =
                                    document.querySelector(
                                        "iframe"
                                    ).contentDocument;
                                if (
                                    !root &&
                                    doc.querySelectorAll(`a[name="${value}"]`)
                                        .length == 0
                                ) {
                                    for (k in ed.formatter.get()) {
                                        ed.formatter.remove(k);
                                    }
                                    ed.formatter.apply("labeltarget", {
                                        value: value,
                                        value2: window.labelTypeVar,
                                    });
                                    // CALL MODEL ADD
                                    // callModel(...)
                                }
                            }
                            ed.fire("Change");
                            clearLabeltargetTippys();
                            setLabeltargetTippys();
                            window.labelTypeVar = null;
                            formApi.hide();
                        },
                    },
                ],
            });

            ed.ui.registry.addContextForm("labelref-form", {
                label: "Label reference",
                initValue: function () {
                    var root = getRoot(
                        ed.selection.getNode(),
                        "labelref_class"
                    );
                    return root ? root.getAttribute("href").substring(1) : "";
                },
                position: "selection",
                predicate: calcLabelrefPredicate,
                commands: [
                    {
                        type: "contextformbutton",
                        icon: "checkmark",
                        tooltip: "Add reference",
                        primary: true,
                        onAction: function (formApi) {
                            var value = formApi.getValue();
                            if (value) {
                                var root = getRoot(
                                    ed.selection.getNode(),
                                    "labelref_class"
                                );
                                if (!root) {
                                    for (k in ed.formatter.get()) {
                                        ed.formatter.remove(k);
                                    }
                                    ed.formatter.apply("labelref", {
                                        value: `#${value}`,
                                    });
                                } else {
                                    root.setAttribute("href", `#${value}`);
                                }
                            }
                            ed.fire("Change");
                            formApi.hide();
                        },
                    },
                    {
                        type: "contextformbutton",
                        icon: "close",
                        tooltip: "Remove reference",
                        onAction: function (formApi) {
                            var root = getRoot(
                                ed.selection.getNode(),
                                "labelref_class"
                            );
                            if (root) {
                                root.removeAttribute("href");
                                root.removeAttribute("style");
                                ed.formatter.remove("labelref", null, root);
                            }
                            ed.fire("Change");
                            formApi.hide();
                        },
                    },
                ],
            });
        },
    });
};
