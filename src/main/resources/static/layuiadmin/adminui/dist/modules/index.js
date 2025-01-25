/** The Web UI Theme-v2.5.1 */
;layui.define("admin", function (e) {
    function a(e) {
        var n, a = layui.router(), i = u(l.container), t = r.correctRouter(a.path.join("/"));
        if (layui.each(l.indPage, function (e, a) {
            if (t === a) return n = !0
        }), layui.config({base: l.paths.base + "modules/"}), n || "/user/login" === t) i.render(a.path.join("/")).done(function () {
            r.pageType = "alone"
        }); else {
            if (l.interceptor) if (!layui.data(l.tableName)[l.request.tokenName]) return location.hash = "/user/login/redirect=" + encodeURIComponent(a.href || "");
            "console" === r.pageType ? c() : i.render("layout").done(function () {
                c(), layui.element.render(), r.screen() < 2 && r.sideFlexible(), r.pageType = "console"
            })
        }
    }

    var l = layui.setter, o = layui.element, r = layui.admin, s = r.tabsPage, u = layui.view, c = function () {
            function e(e) {
                c.haveInit && h(".layui-layer").each(function () {
                    var e = h(this), a = e.attr("times");
                    e.hasClass("layui-layim") || e.hasClass("layui-layim-chat") || layer.close(a)
                }), c.haveInit = !0, h(d).scrollTop(0), delete s.type
            }

            var i = layui.router(), a = i.path, t = r.correctRouter(i.path.join("/"));
            "" === (a = a.length ? a : [""])[a.length - 1] && (a[a.length - 1] = l.entry);
            if ("tab" === s.type && ("/" !== t || "/" === t && r.tabsBody().html())) return r.tabsBodyChange(s.index), e(s.type);
            u().render(a.join("/")).then(function (e) {
                var a, n = h("#LAY_app_tabsheader>li");
                n.each(function (e) {
                    h(this).attr("lay-id") === t && (a = !0, s.index = e)
                }), l.pageTabs && "/" !== t && !a && (h(d).append('<div class="layadmin-tabsbody-item layui-show"></div>'), s.index = n.length, o.tabAdd(y, {
                    title: "<span>" + (e.title || "\u65b0\u6807\u7b7e\u9875") + "</span>",
                    id: t,
                    attr: i.href
                })), this.container = r.tabsBody(s.index), l.pageTabs || this.container.scrollTop(0), o.tabChange(y, t), r.tabsBodyChange(s.index)
            }).done(function () {
                layui.use("common", layui.cache.callback.common), n.on("resize", layui.data.resize), o.render("breadcrumb", "breadcrumb"), r.tabsBody(s.index).on("scroll", function () {
                    var e = h(this), a = h(".layui-laydate"), n = h(".layui-layer")[0];
                    a[0] && (a.each(function () {
                        var e = h(this);
                        e.hasClass("layui-laydate-static") || e.remove()
                    }), e.find("input").blur()), n && layer.closeAll("tips")
                })
            }), e()
        }, d = "#LAY_app_body", y = "layadmin-layout-tabs", h = layui.$, n = h(window),
        i = (layui.link(l.paths.core + "css/admin.css?v=" + r.v, function () {
            a()
        }, "layuiAdmin"), window.onhashchange = function () {
            a(), layui.event.call(this, l.MOD_NAME, "hash({*})", layui.router())
        }, {render: c});
    h.extend(r, i), e("adminIndex", i)
});