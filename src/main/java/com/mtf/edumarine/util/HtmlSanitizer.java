package com.mtf.edumarine.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

/**
 * 회원이 작성하는 HTML(커뮤니티 본문, Quill 에디터 출력)을 저장·출력 전에 정화한다.
 * Quill 이 만드는 태그·클래스·인라인 스타일·이미지(/board/uploadFileGet, data:)는 유지하고
 * script / iframe / 이벤트 속성 / javascript: 링크는 제거한다.
 *
 * 2026-10-30 보안 점검 조치(H-4)로 추가.
 */
public final class HtmlSanitizer {

    private static final Safelist SAFELIST = Safelist.relaxed()
            .addTags("div", "s", "span", "u", "hr", "font", "del", "ins", "mark")
            .addAttributes(":all", "class", "style", "align")
            .addAttributes("a", "target", "rel")
            .addAttributes("img", "width", "height", "alt", "title")
            .addAttributes("font", "color", "face", "size")
            .addAttributes("td", "colspan", "rowspan", "width", "height")
            .addAttributes("th", "colspan", "rowspan", "width", "height")
            .addAttributes("table", "border", "cellpadding", "cellspacing", "width")
            .addProtocols("img", "src", "data")
            .preserveRelativeLinks(true);

    private static final Document.OutputSettings OUTPUT = new Document.OutputSettings().prettyPrint(false);

    private HtmlSanitizer() {
    }

    /**
     * HTML 정화. null 은 null, 빈 문자열은 빈 문자열을 돌려준다.
     */
    public static String clean(String html) {
        if (html == null || html.isEmpty()) {
            return html;
        }
        return Jsoup.clean(html, "", SAFELIST, OUTPUT);
    }
}
