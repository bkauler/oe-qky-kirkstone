
# ash shebang:
# ref: https://bkhome.org/news/201806/rox-filer-shared-mime-info-tweaks.html
# pie executable:
# ref: https://bkhome.org/news/202010/file-utility-confuses-executable-and-shared-library.html

# binary executables are identified as application/x-sharedlib
# ref: https://gitlab.freedesktop.org/xdg/shared-mime-info/-/commit/15fb989154e0eacde8c63222b60f731ed7a462b1
# ref: https://gitlab.freedesktop.org/xdg/shared-mime-info/-/merge_requests/81.patch

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://shared-mime-info-shebang-ash.patch \
            file://pie-executable.patch"

