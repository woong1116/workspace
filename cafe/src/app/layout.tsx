import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import { ThemeSync } from "@/components/common/ThemeSync";
import { ToastProvider } from "@/components/common/Toast";
import { Header } from "@/components/layout/Header";
import { MobileNav } from "@/components/layout/MobileNav";
import { Sidebar } from "@/components/layout/Sidebar";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "라이언 카페",
  description: "카페 매장용 POS(Point of Sale) 웹 애플리케이션",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html
      lang="ko"
      className={`${geistSans.variable} ${geistMono.variable} h-full antialiased`}
    >
      <body className="flex min-h-full flex-col bg-background text-foreground">
        <ThemeSync />
        <ToastProvider>
          <div className="flex min-h-screen flex-1">
            <Sidebar />
            <div className="flex flex-1 flex-col">
              <Header />
              <main className="flex flex-1 flex-col overflow-y-auto bg-slate-50 p-6 dark:bg-slate-950">
                {children}
              </main>
              <MobileNav />
            </div>
          </div>
        </ToastProvider>
      </body>
    </html>
  );
}
